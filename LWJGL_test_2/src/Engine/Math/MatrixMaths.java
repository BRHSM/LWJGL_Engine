package Engine.Math;

import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;

import Engine.DisplayRenderEngine.AbstractCamera;
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
	
	public static Matrix4f createProjectionMatrix(float aspectRatio, float fov, float nearPlane, float farPlane) {
        float yScale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustumLength = nearPlane - farPlane;
		Matrix4f projectionMatrix = new Matrix4f();
		
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((farPlane + nearPlane) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * farPlane * nearPlane) / frustumLength);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}
	
    public static Matrix4f createViewMatrix(AbstractCamera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRoll()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }

	public static Matrix4f getAllOneMatrix() {
		Matrix4f tmp = new Matrix4f();
		tmp.m00 = 1;
		tmp.m01 = 1;
		tmp.m02 = 1;
		tmp.m03 = 1;
		tmp.m10 = 1;
		tmp.m11 = 1;
		tmp.m12 = 1;
		tmp.m13 = 1;
		tmp.m20 = 1;
		tmp.m21 = 1;
		tmp.m22 = 1;
		tmp.m23 = 1;
		tmp.m30 = 1;
		tmp.m31 = 1;
		tmp.m32 = 1;
		tmp.m33 = 1;
		return tmp;
	}
}
