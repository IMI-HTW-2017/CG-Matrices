package ab3;

import ab3.math.Vector3;
import ab3.objects.Cube;
import ab3.objects.Sphere;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.Texture;

import static org.lwjgl.opengl.GL30.*;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private Cube cube;
	private Sphere sphere;

	private int counter;
	private float counter2;

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		cube = new Cube(0, 0, -2, "aufgabe3");
		sphere = new Sphere(0, 0, -2, "sphere");

		//z-Buffer und backface culling aktivieren
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);

        //Light
		Light light = new Light(new Vector3(2, 4, 1.5f), new Vector3(0.8f, 0.8f, 0.8f), 5);

		int uniformLightPositionID = glGetUniformLocation(sphere.getShaderProgram().getId(), "lightPosition");
		int uniformLightColorID = glGetUniformLocation(sphere.getShaderProgram().getId(), "lightColor");
		int uniformLightIntensityID = glGetUniformLocation(sphere.getShaderProgram().getId(), "lightIntensity");
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
			sphere.getModelMatrix().rotateX(1);
        else
			sphere.getModelMatrix().rotateY(1);
        counter = (counter + 1) % 90;

        if (counter2 < 16)
			sphere.getModelMatrix().scale(1.03f);
        else
			sphere.getModelMatrix().scale(1 / 1.03f);
        counter2 = (counter2 + 1) % 32;
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen
        int uniformModelMatrixID = glGetUniformLocation(sphere.getShaderProgram().getId(), "modelMatrix");
        glUniformMatrix4fv(uniformModelMatrixID, false, sphere.getModelMatrix().getValuesAsArray());

		// VAOs zeichnen
        glDrawArrays(GL_TRIANGLES, 0, 36);
	}

	@Override
	public void destroy() {
	}
}
