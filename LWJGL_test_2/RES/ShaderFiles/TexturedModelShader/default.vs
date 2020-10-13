#version 330

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

void main(void){

	gl_Position = vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
	
}