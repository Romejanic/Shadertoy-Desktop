#version 330 core

out vec4 out_fragColor;

// directives for shader developers to use
#define __SHADERTOY_DESKTOP__
#define __DESKTOP_REALTIME // this is defined if the shader is being viewed
//#define __DESKTOP_RENDER // this is defined if the shader is being rendered out
#define __FRAG_DEPTH gl_FragCoord.z
#define __FRAG_UV v_uv
#define glFragColor out_fragColor

void mainImage(out vec4 fragColor, in vec2 fragCoord);

/***************SHADER CODE GOES HERE****************/
%s
/***************SHADER CODE ENDS HERE****************/

void main() {
	mainImage(out_fragColor, gl_FragCoord.xy);
}