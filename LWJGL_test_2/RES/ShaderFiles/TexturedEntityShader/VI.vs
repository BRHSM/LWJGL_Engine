#version 150

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrixBasic;
uniform mat4 projectionMatrixBasic;
uniform mat4 viewMatrixBasic;

void main(void){
	gl_Position = projectionMatrixBasic * viewMatrixBasic * transformationMatrixBasic * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
}