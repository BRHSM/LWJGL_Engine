#version 150

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrixTextured;
uniform mat4 projectionMatrixTextured;

void main(void){
	gl_Position = projectionMatrixTextured * transformationMatrixTextured * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
}