#version 150

in vec3 color;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main(void){

	out_Color = vec4(color,1.0);

}