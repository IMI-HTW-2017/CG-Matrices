package ab3.objects;

import ab3.math.Matrix4;
import ab3.math.Vector3;
import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Object {

    float[] vertices;
    float[] normals;
    float[] uvs;

    private Matrix4 modelMatrix;
    private Matrix4 viewMatrix;

    private String shader;
    private ShaderProgram shaderProgram;
    private int vaoID;

    Object(Vector3 position, Vector3 rotation, Vector3 scale, String shader) {
        this.shader = shader;

        modelMatrix = new Matrix4();
        viewMatrix = new Matrix4();

        viewMatrix.translate(position.x(), position.y(), position.z());
        viewMatrix.rotateX(rotation.x()).rotateY(rotation.y()).rotateZ(rotation.z());
        viewMatrix.scale(scale.x(), scale.y(), scale.z());
    }

    float[] calculateObjectNormals(float[] vertices) {
        float[] normals = new float[vertices.length];
        for (int i = 0; i < vertices.length; i += 9) {
            Vector3 vector1 = new Vector3(vertices[i + 3] - vertices[i], vertices[i + 4] - vertices[i + 1], vertices[i + 5] - vertices[i + 2]);
            Vector3 vector2 = new Vector3(vertices[i + 6] - vertices[i], vertices[i + 7] - vertices[i + 1], vertices[i + 8] - vertices[i + 2]);
            Vector3 normal = vector1.cross(vector2).normalize();

            for (int j = 0; j < 3; j++) {
                normals[i + j * 3] = normal.x();
                normals[i + j * 3 + 1] = normal.y();
                normals[i + j * 3 + 2] = normal.z();
            }
        }

        return normals;
    }

    void init() {
        shaderProgram = new ShaderProgram(shader);
        glUseProgram(shaderProgram.getId());

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        createVBO(vertices, 0, 3);
        if (normals != null)
            createVBO(normals, 1, 3);
        if (uvs != null)
            createVBO(uvs, 2, 2);

        //View matrix & projection matrix
        int uniformViewMatrixID = glGetUniformLocation(shaderProgram.getId(), "viewMatrix");
        int uniformProjectionMatrixID = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
        glUniformMatrix4fv(uniformViewMatrixID, false, viewMatrix.getValuesAsArray());
        glUniformMatrix4fv(uniformProjectionMatrixID, false, new Matrix4(0.5F, 100F).getValuesAsArray());
    }

    private void createVBO(float[] values, int index, int size) {
        int vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, values, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    public void bindShader() {
        glUseProgram(shaderProgram.getId());
    }

    public void render() {
        glBindVertexArray(vaoID);
        glDrawArrays(GL_TRIANGLES, 0, getNumberOfVertices());
    }

    public Matrix4 getModelMatrix() {
        return modelMatrix;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public int getNumberOfVertices() {
        return vertices.length / 3;
    }
}
