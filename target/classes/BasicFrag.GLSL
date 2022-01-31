#version 330 core
out vec4 FragColor;
in vec4 vertexColor; // the input variable from the vertex shader (same name and same type)
in vec2 texCoors;
uniform sampler2D myTexture;

void main()
{
    FragColor =  texture(myTexture, texCoors);
    //FragColor = vec4(1.0f,1.0f,1.0f,0);
}