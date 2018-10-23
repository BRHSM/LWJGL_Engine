#version 150

in vec3 position;
in vec2 textureCoordinates;

out vec2 passTextureCoordinates;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

void main(void){
	gl_Position = projectionMatrix * transformationMatrix * vec4(position,1.0);
	passTextureCoordinates = textureCoordinates;
}