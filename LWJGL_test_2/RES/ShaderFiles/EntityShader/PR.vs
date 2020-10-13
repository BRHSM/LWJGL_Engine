#version 330

in vec3 position;

out vec3 color;

uniform mat4 transformationMatrixBasic;
uniform mat4 projectionMatrixBasic;

void main(void){
	gl_Position = projectionMatrixBasic * transformationMatrixBasic * vec4(position,1.0);
	color = vec3(1.0,1.0,1.0);
}