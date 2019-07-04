#version 330 core

layout(location = 0) in vec2 vertex;
out vec2 v_uv;

void main() {
	gl_Position = vec4(vertex, 0., 1.);
	v_uv = vertex.xy * .5 + .5;
}