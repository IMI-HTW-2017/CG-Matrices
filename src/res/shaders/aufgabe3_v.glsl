#version 400

layout(location = 0) in vec3 vertex;
layout(location = 1) in vec3 normal;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

out vec3 vertexColor;
out vec3 normalVector;
out vec4 pixelPosition;

void main() {
    /************/
    /* RGB-Cube */
    /************/

    //black
    if (vertex.x == -0.5 && vertex.y == -0.5 && vertex.z == -0.5) {
        vertexColor = vec3(0.0, 0.0, 0.0);
    //red
    } else if (vertex.x == -0.5 && vertex.y == 0.5 && vertex.z == -0.5) {
        vertexColor = vec3(1.0, 0.0, 0.0);
    //green
    } else if (vertex.x == -0.5 && vertex.y == -0.5 && vertex.z == 0.5) {
        vertexColor = vec3(0.0, 1.0, 0.0);
    //blue
    } else if (vertex.x == 0.5 && vertex.y == -0.5 && vertex.z == -0.5) {
        vertexColor = vec3(0.0, 0.0, 1.0);
    //magenta
    } else if (vertex.x == 0.5 && vertex.y == 0.5 && vertex.z == -0.5) {
        vertexColor = vec3(1.0, 0.0, 1.0);
    //yellow
    } else if (vertex.x == -0.5 && vertex.y == 0.5 && vertex.z == 0.5) {
        vertexColor = vec3(1.0, 1.0, 0.0);
    //cyan
    } else if (vertex.x == 0.5 && vertex.y == -0.5 && vertex.z == 0.5) {
        vertexColor = vec3(0.0, 1.0, 1.0);
    //white
    } else if (vertex.x == 0.5 && vertex.y == 0.5 && vertex.z == 0.5) {
        vertexColor = vec3(1.0, 1.0, 1.0);
    } else {
        vertexColor = vec3(0.5, 0.5, 0.5);
    }

    /**************************/
    /* Einfarbige Seiten Cube */
    /**************************/

    /*if (gl_VertexID < 6) {
        vertexColor = vec3(1.0, 0.0, 0.0);
    } else if (gl_VertexID < 12) {
        vertexColor = vec3(0.0, 1.0, 0.0);
    } else if (gl_VertexID < 18) {
        vertexColor = vec3(0.0, 0.0, 1.0);
    } else if (gl_VertexID < 24) {
        vertexColor = vec3(1.0, 1.0, 0.0);
    } else if (gl_VertexID < 30) {
        vertexColor = vec3(1.0, 0.0, 1.0);
    } else {
        vertexColor = vec3(0.0, 1.0, 1.0);
    }*/

    /***************************/
    /* Einfarbige Seiten Tetra */
    /***************************/
    /*if (gl_VertexID < 3) {
        vertexColor = vec3(1.0, 0.0, 0.0);
    } else if (gl_VertexID < 6) {
        vertexColor = vec3(0.0, 1.0, 0.0);
    } else if (gl_VertexID < 9) {
        vertexColor = vec3(0.0, 0.0, 1.0);
    } else if (gl_VertexID < 12) {
        vertexColor = vec3(1.0, 0.0, 1.0);
    } else {
        vertexColor = vec3(0.0, 1.0, 1.0);
    }*/

    mat3 normalMatrix = inverse(transpose(mat3(transformationMatrix)));
    normalVector = normalMatrix * normal;

    pixelPosition = transformationMatrix * vec4(vertex, 1.0);
    gl_Position = projectionMatrix * pixelPosition;
}