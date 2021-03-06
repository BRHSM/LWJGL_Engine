#version 150

in vec3 position;

out vec3 color;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform int useProjectionMatrix;
uniform mat4 viewMatrix;
uniform int useViewMatrix;

void main(void){
	if (useViewMatrix != 0) {
		if (useProjectionMatrix != 0) {
			gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position,1.0);
		} else {
			gl_Position = viewMatrix * transformationMatrix * vec4(position,1.0);
		}
	} else {
		if (useProjectionMatrix != 0) {
			gl_Position = projectionMatrix * transformationMatrix * vec4(position,1.0);
		} else {
			gl_Position = transformationMatrix * vec4(position,1.0);
		}
	}
	color = vec3(1.0,1.0,1.0);
}