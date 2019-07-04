#version 330 core

out vec4 out_fragColor;
in vec2 v_uv;

// directives for shader developers to use
#define __SHADERTOY_DESKTOP__
#define __DESKTOP_REALTIME // this is defined if the shader is being viewed
//#define __DESKTOP_RENDER // this is defined if the shader is being rendered out
#define __FRAG_DEPTH gl_FragCoord.z
#define __FRAG_UV v_uv
#define glFragColor out_fragColor

// program defined directives
%s

// uniform variables
uniform float iTime;
uniform float iTimeDelta;
uniform vec3 iResolution;
uniform int iFrame;
uniform float iChannelTime[4];
uniform vec3 iChannelResolution[4];
uniform vec4 iMouse;
uniform vec4 iDate;
uniform float iSampleRate;
// samplers
%s

// mainImage() definition
void mainImage(out vec4 fragColor, in vec2 fragCoord);

/***************SHADER CODE GOES HERE****************/
%s
/***************SHADER CODE ENDS HERE****************/

void main() {
	mainImage(out_fragColor, gl_FragCoord.xy);
}