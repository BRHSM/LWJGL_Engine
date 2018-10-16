#version 150

in vec2 passTextureCoordinates;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main(void){

	out_Color = texture(textureSampler,passTextureCoordinates);

}