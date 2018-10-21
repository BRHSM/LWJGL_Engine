package Engine.Data.ModelHandeling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;

/** This class is used to read .obj files and create instances of the OBJModel class
 *  which can then be converted into actual model types. 
 *
 * @author user
 * @version 1.0
 * @since 1.0
 * @see OBJModel
 */
public class OBJLoader {
	/** create an OBJModel from the file specified in the filename.
	 * 
	 * @param fileName the filename to load (filename must not contain extention and is searched in the model 
	 *  folder configured in the EngineOptions config file).
	 * @return an OBJModel containing all info 
	 */
	public static OBJModel loadOBJFile(String fileName) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(getFilePath(fileName)));
			
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR]: Could not load file: " + fileName);
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fileReader);
		String line;
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		ArrayList<Vector2f> textureCoords = new ArrayList<Vector2f>();
		ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		float[] textureArray = null;
		float[] normalsArray = null;
		boolean hasNext = true;
		
		try {
			do {
				line = reader.readLine();
				String[] currentLine = line.split(" ");
				if(line.startsWith("v ")) {
					vertices.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3])));
				}
				if(line.startsWith("vt ")) {
					textureCoords.add(new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2])));				
				}
				if(line.startsWith("vn ")) {
					normals.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3])));
				}
				if(line.startsWith("f ")) {
					textureArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];
					hasNext = false;
				}
			} while(hasNext);
			
			while(line!= null) {
				if(!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				processVertex(vertex1,textureCoords,normals,indices,textureArray,normalsArray);
				processVertex(vertex2,textureCoords,normals,indices,textureArray,normalsArray);
				processVertex(vertex3,textureCoords,normals,indices,textureArray,normalsArray);
				
				line = reader.readLine();
			}
			
			reader.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return new OBJModel(vertices, textureCoords, normals, indices);
		
	}
	
	/** get a full path for 
	 * 
	 * @param fileName The filename you want the path for.
	 * @return The full path of the file.
	 */
	private static String getFilePath(String fileName) {
		return OptionHandler.getProperty(EngineOptions.PATHMODELS_KEY, OptionHandler.ENGINE_OPTION_ID) + fileName + ".obj";
	}
	
	private static void processVertex(String[] vertexData, ArrayList<Vector2f> textureCoords, ArrayList<Vector3f> normals, ArrayList<Integer> indices, float[] textureArray, float[] normalsArray) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);
		Vector2f currentTexture = textureCoords.get(Integer.parseInt(vertexData[1]) - 1);
		textureArray[currentVertexPointer * 2] = currentTexture.x;
		textureArray[currentVertexPointer * 2 + 1] = 1 - currentTexture.y;
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalsArray[currentVertexPointer * 3] = currentNorm.x;
		normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
		
	}
}
