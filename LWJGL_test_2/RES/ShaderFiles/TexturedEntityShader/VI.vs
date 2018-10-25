#version 150

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrixTextured;
uniform mat4 projectionMatrixTextured;
uniform mat4 viewMatrixTextured;

void main(void){
	gl_Position = projectionMatrixTextured * viewMatrixTextured * transformationMatrixTextured * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
}