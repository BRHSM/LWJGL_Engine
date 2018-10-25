package Engine.Data.ModelHandeling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;

import Engine.Core.Core.IDGenerator;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;

/** This class is used to read .obj files and create instances of the OBJModel class
 *  which can then be converted into actual model types. 
 *
 * @author user
 * @version 1.0
 * @since 1.0
 * @see OBJModel
 * @see Vertex
 */
public class OBJLoader {
	/** create an OBJModel from the file specified in the filename.
	 * 
	 * @param fileName the filename to load (filename must not contain extention and is searched in the model 
	 *  folder configured in the EngineOptions config file).
	 * @return an OBJModel containing all info 
	 */
	public static OBJModel loadOBJ(String fileName, int id) {
        FileReader isr = null;
        File objFile = new File(OptionHandler.getProperty(EngineOptions.PATHMODELS_KEY, OptionHandler.ENGINE_OPTION_ID) + fileName + ".obj");
        try {
            isr = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR]: could not load model: " + OptionHandler.getProperty(EngineOptions.PATHMODELS_KEY, OptionHandler.ENGINE_OPTION_ID) + fileName + ".obj");
        }
        BufferedReader reader = new BufferedReader(isr);
        String line;
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Vector2f> textures = new ArrayList<Vector2f>();
        ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        try {
            while (true) {
                line = reader.readLine();
                if (line.startsWith("v ")) {
                    String[] currentLine = line.split(" ");
                    Vector3f vertex = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    Vertex newVertex = new Vertex(vertices.size(), vertex);
                    vertices.add(newVertex);
 
                } else if (line.startsWith("vt ")) {
                    String[] currentLine = line.split(" ");
                    Vector2f texture = new Vector2f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    String[] currentLine = line.split(" ");
                    Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    normals.add(normal);
                } else if (line.startsWith("f ")) {
                    break;
                }
            }
            while (line != null && line.startsWith("f ")) {
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
                processVertex(vertex1, vertices, indices);
                processVertex(vertex2, vertices, indices);
                processVertex(vertex3, vertices, indices);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("[ERROR]: Error while reading obj file");
        }
        removeUnusedVertices(vertices);
        float[] verticesArray = new float[vertices.size() * 3];
        float[] texturesArray = new float[vertices.size() * 2];
        float[] normalsArray = new float[vertices.size() * 3];
        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray, texturesArray, normalsArray);
        int[] indicesArray = convertIndicesListToArray(indices);
        OBJModel data = new OBJModel(verticesArray, texturesArray, normalsArray, indicesArray, furthest, id);
        return data;
    }
	
	/** Process a vertex from the .obj file.
	 * 
	 * @param vertexData The data of the file line.
	 * @param textureCoords The texturecoords arraylist.
	 * @param normals The normals arraylist.
	 * @param indices The indices arraylist.
	 * @param textureArray The textureCoords floatArray.
	 * @param normalsArray The normals floatArray.
	 */
    private static void processVertex(String[] vertex, ArrayList<Vertex> vertices, ArrayList<Integer> indices) {
        int index = Integer.parseInt(vertex[0]) - 1;
        Vertex currentVertex = vertices.get(index);
        int textureIndex = Integer.parseInt(vertex[1]) - 1;
        int normalIndex = Integer.parseInt(vertex[2]) - 1;
        if (!currentVertex.isSet()) {
            currentVertex.setTextureIndex(textureIndex);
            currentVertex.setNormalIndex(normalIndex);
            indices.add(index);
        } else {
            dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
                    vertices);
        }
    }
 
    /** Converts an arraylist of indices to an array containing the same information.
     * 
     * @param indices The indices to convert.
     * @return An array of indices.
     */
    private static int[] convertIndicesListToArray(ArrayList<Integer> indices) {
        int[] indicesArray = new int[indices.size()];
        for (int i = 0; i < indicesArray.length; i++) {
            indicesArray[i] = indices.get(i);
        }
        return indicesArray;
    }
    
    /** Converts the data from ArrayLists to Arrays.
     * 
     * @param vertices The vertices ArrayList.
     * @param textures The textures ArrayList.
     * @param normals The normals ArrayList.
     * @param verticesArray The vertices array.
     * @param texturesArray The textures array.
     * @param normalsArray The normals array.
     * @return the furthestPoint of the model.
     */
    private static float convertDataToArrays(ArrayList<Vertex> vertices, ArrayList<Vector2f> textures, ArrayList<Vector3f> normals, float[] verticesArray, float[] texturesArray,float[] normalsArray) {
        float furthestPoint = 0;
        for (int i = 0; i < vertices.size(); i++) {
            Vertex currentVertex = vertices.get(i);
            if (currentVertex.getLength() > furthestPoint) {
                furthestPoint = currentVertex.getLength();
            }
            Vector3f position = currentVertex.getPosition();
            Vector2f textureCoord = textures.get(currentVertex.getTextureIndex());
            Vector3f normalVector = normals.get(currentVertex.getNormalIndex());
            verticesArray[i * 3] = position.x;
            verticesArray[i * 3 + 1] = position.y;
            verticesArray[i * 3 + 2] = position.z;
            texturesArray[i * 2] = textureCoord.x;
            texturesArray[i * 2 + 1] = 1 - textureCoord.y;
            normalsArray[i * 3] = normalVector.x;
            normalsArray[i * 3 + 1] = normalVector.y;
            normalsArray[i * 3 + 2] = normalVector.z;
        }
        return furthestPoint;
    }
    
    /** Handles vertex if it's allready processed.
     * 
     * @param previousVertex The last vertex in the list.
     * @param newTextureIndex The next index for the textures list.
     * @param newNormalIndex The next index for the normals list.
     * @param indices The list of indices.
     * @param vertices The list of vertices.
     */
    private static void dealWithAlreadyProcessedVertex(Vertex previousVertex, int newTextureIndex, int newNormalIndex, ArrayList<Integer> indices, ArrayList<Vertex> vertices) {
        if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) {
            indices.add(previousVertex.getIndex());
        } else {
            Vertex anotherVertex = previousVertex.getDuplicateVertex();
            if (anotherVertex != null) {
                dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex, newNormalIndex,
                        indices, vertices);
            } else {
                Vertex duplicateVertex = new Vertex(vertices.size(), previousVertex.getPosition());
                duplicateVertex.setTextureIndex(newTextureIndex);
                duplicateVertex.setNormalIndex(newNormalIndex);
                previousVertex.setDuplicateVertex(duplicateVertex);
                vertices.add(duplicateVertex);
                indices.add(duplicateVertex.getIndex());
            }
 
        }
    }
    
    /** Removes unused vertices from model.
     * 
     * @param vertices The vertices list.
     */
    private static void removeUnusedVertices(ArrayList<Vertex> vertices){
        for(Vertex vertex:vertices){
            if(!vertex.isSet()){
                vertex.setTextureIndex(0);
                vertex.setNormalIndex(0);
            }
        }
    }
}
