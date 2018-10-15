package Math;

import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;
/** Class which holds functions for matrix maths. 
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 */
public class MatrixMaths {
	/** Create a transformationMatrix
	 * 
	 * @param translation the positional translation of the model.
	 * @param rx The X rotation for the model.
	 * @param ry The Y rotation for the model.
	 * @param rz The Z rotation for the model.
	 * @param scale The scale for the model.
	 * @return The transformationMatrix.
	 */
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
}
