package ab3;

import ab3.math.Matrix4;
import ab3.math.Vector3;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

import static org.lwjgl.opengl.GL30.*;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;

	private Matrix4 transformationMatrix;
	private Matrix4 modelMatrix;
	private Matrix4 viewMatrix;

	private int counter;
	private float counter2;

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		int vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);

		//Vertices Stuff
		int vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);

		//Cube
        float[] vertices = new float[] {
                //vorne
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, 0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,

                //hinten
                -0.5F, -0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                -0.5F, 0.5F, -0.5F,
                0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //unten
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //oben
                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,

                //links
                -0.5F, 0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, -0.5F,

                //rechts
                0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, 0.5F, -0.5F
        };

        /*float val = (float) (Math.sqrt(3) / 4);

        //Triangle-thingy
		float[] vertices = new float[] {
				//front
				0.5f, val, 0,
				-0.5f, val, 0,
				0, -val, 0.5f,

				//left
				-0.5f, val, 0,
				0, -val, -0.5f,
				0, -val, 0.5f,

				//right
				0.5f, val, 0,
				0, -val, 0.5f,
				0, -val, -0.5f,

				//back
				-0.5f, val, 0,
				0.5f, val, 0,
				0, -val, -0.5f
		};*/

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);

		//Normals Stuff
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);

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

		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);

		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);

		//UV Stuff
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);

		float[] uvs = new float[] {
				//vorne
				0, 1,
				1, 1,
				0, 0,

				0, 0,
				1, 1,
				1, 0,

				//hinten
				1, 1,
				1, 0,
				0, 1,

				1, 0,
				0, 0,
				0, 1,

				//unten
				0, 0,
				1, 1,
				1, 0,

				0, 0,
				0, 1,
				1, 1,

				//oben
				0, 1,
				1, 1,
				1, 0,

				0, 1,
				1, 0,
				0, 0,

				//links
				1, 0,
				0, 0,
				1, 1,

				1, 1,
				0, 0,
				0, 1,

				//rechts
				0, 0,
				0, 1,
				1, 0,

				0, 1,
				1, 1,
				1, 0
		};

		glBufferData(GL_ARRAY_BUFFER, uvs, GL_STATIC_DRAW);

		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(2);

		//z-Buffer und backface culling aktivieren
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);

		//Projection Matrix
        int uniformProjectionMatrixID = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
        glUniformMatrix4fv(uniformProjectionMatrixID, false, new Matrix4(0.5F, 100F).getValuesAsArray());

        modelMatrix = new Matrix4();
        viewMatrix = new Matrix4().translate(0, 0, -2);

        //Light
		Light light = new Light(new Vector3(2, 4, 1.5f), new Vector3(0.8f, 0.1f, 0.1f), 3);

		int uniformLightPositionID = glGetUniformLocation(shaderProgram.getId(), "lightPosition");
		int uniformLightColorID = glGetUniformLocation(shaderProgram.getId(), "lightColor");
		int uniformLightIntensityID = glGetUniformLocation(shaderProgram.getId(), "lightIntensity");
		glUniform3fv(uniformLightPositionID, light.getPosition().getValues());
		glUniform3fv(uniformLightColorID, light.getColor().getValues());
		glUniform1f(uniformLightIntensityID, light.getIntensity());

		//Textures
		Texture cubeTexture = new Texture("metal.jpg");
		glBindTexture(GL_TEXTURE_2D, cubeTexture.getId());
	}

	@Override
	public void update() {
		// Transformation durchführen (Matrix anpassen)
        if (counter < 45)
			modelMatrix.rotateX(1);
        else
			modelMatrix.rotateY(1);
        counter = (counter + 1) % 90;

        if (counter2 < 16)
        	modelMatrix.scale(1.03f);
        else
        	modelMatrix.scale(1 / 1.03f);
        counter2 = (counter2 + 1) % 32;

        transformationMatrix = new Matrix4(modelMatrix).multiply(viewMatrix);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen
        int uniformTransformationMatrixID = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
        glUniformMatrix4fv(uniformTransformationMatrixID, false, transformationMatrix.getValuesAsArray());

		// VAOs zeichnen
        glDrawArrays(GL_TRIANGLES, 0, 36);
	}

	@Override
	public void destroy() {
	}
}
