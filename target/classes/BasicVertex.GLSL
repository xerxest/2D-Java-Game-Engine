#version 330 core
layout (location = 0) in vec4 aPos;
layout (location = 1) in vec2 atexCoors;
out vec4 vertexColor;
out vec2 texCoors;


uniform mat4 proj;
uniform mat4 view;


void main()
{
    gl_Position = proj * view * aPos;
    vertexColor = vec4(0.5, 0.0, 0.0, 1.0);
    texCoors = atexCoors;
}