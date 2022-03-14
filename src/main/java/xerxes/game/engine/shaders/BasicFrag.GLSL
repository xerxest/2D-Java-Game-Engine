#version 330 core
out vec4 FragColor;
in vec2 texCoors;
uniform sampler2D myTexture;

void main()
{
    FragColor =  vec4(1.0,0.0,0.0,1.0);
}