#version 150

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrix;

void main(void){

	gl_Position = transformationMatrix * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
	
}