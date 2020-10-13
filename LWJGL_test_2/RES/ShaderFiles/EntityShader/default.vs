#version 330

in vec3 position;

out vec3 color;

uniform mat4 transformationMatrixBasic;

void main(void){
	gl_Position = transformationMatrixBasic * vec4(position,1.0);
	color = vec3(1.0,1.0,1.0);
}