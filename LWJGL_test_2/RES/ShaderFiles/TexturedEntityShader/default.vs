#version 330

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrixBasic;

void main(void){
	gl_Position = transformationMatrixBasic * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
}