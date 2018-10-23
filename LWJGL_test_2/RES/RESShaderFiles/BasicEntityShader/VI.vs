#version 150

in vec3 position;

out vec3 color;

uniform mat4 transformationMatrixBasic;
uniform mat4 projectionMatrixBasic;
uniform mat4 viewMatrixBasic;

void main(void){
	gl_Position = projectionMatrixBasic * viewMatrixBasic * transformationMatrixBasic * vec4(position,1.0);
	color = vec3(1.0,1.0,1.0);
}