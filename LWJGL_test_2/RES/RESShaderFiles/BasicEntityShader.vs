#version 150

in vec3 position;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

void main(void){

	gl_Position = projectionMatrix * transformationMatrix * vec4(position,1.0);
}