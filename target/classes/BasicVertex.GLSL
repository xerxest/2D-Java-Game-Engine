#version 330 core
layout (location = 0) in vec4 aPos; // the position variable has attribute position
layout (location = 1) in vec2 atexCoors;
out vec4 vertexColor; // specify a color output to the fragment shader
out vec2 texCoors;


uniform mat4 proj;
uniform mat4 view;


void main()
{
    gl_Position = proj * view * aPos; // see how we directly give a vec3 to vec4's constructor
    vertexColor = vec4(0.5, 0.0, 0.0, 1.0); // set the output variable to a dark-red color
    texCoors = atexCoors;
}