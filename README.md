# CG-Matrices
## Exercise 3 - Matrizen

Implementieren Sie in Java eine Klasse Matrix4 und verwenden Sie diese zur Umsetzung von Transformationen im Vertex-Shader.

1. Implementieren Sie in Java eine Klasse Matrix4 gemäß der bei Moodle verfügbaren Vorlage.
2. Erstellen Sie eine Rotationsmatrix um die z-Achse mit sich stetig änderndem Winkel in Java und verwenden Sie diese im Vertex-Shader, indem Sie sie in eine uniform Variable vom Typ mat4 übertragen.
3. Verwenden Sie mehrere Transformationen nacheinander, z.B. Translation und Rotation. Spielt die Reihenfolge eine Rolle?
4. Verwenden Sie eine Matrix zur perspektivischen Projektion und experimentieren Sie mit den Parametern.
5. Wenn Sie 1.-4. erfolgreich bearbeitet haben, steht Ihnen nun die dreidimensionale Welt offen: Erstellen Sie ein oder mehrer 3D-Objekte (z.B. platonische Körper) und animieren Sie diese im Raum. Erzeugen Sie die Objekte in lokalen Koordinaten um den Nullpunkt und bewegen Sie sie dann per Matrix an ihre Position in Weltkoordindaten. Achten Sie auf den sichtbaren Bereich vor allem bezüglich der z-Koordinate!

Nach Bearbeitung dieser Aufgaben sollten Sie in der Lage sein, ...

- Matrizen elegant in Java zu handhaben und an Shader zu übertragen.
- einfache Animationen über Transformationen zu realisieren.
- das Konzept einer Kamera inklusive perspektivischer Projektion umsetzen zu können.
- dreidimensionale Objekte zu modellieren und im Raum zu platzieren.

## Exercise 4 - Normalen und Beleuchtung

Verwenden Sie Normalenvektoren und grundlegende Beleuchtungsverfahren.

1. Färben Sie die Flächen eines 3D-Objekts mit Gouraud-Schattierung - Beobachtung: Das hatten wir schon!
2. Ergänzen Sie Ihre Objekte um ein VBO mit Normalenvektoren pro Ecke. Woher kommen die Werte dafür und müssen diese sehr präzise sein?
3. Was passiert mit Normalenvektoren bei Transformationen des Objekts? Berechnen Sie die "normal matrix" als inverse transponierte Matrix aus dem Produkt view matrix * model matrix und wenden Sie diese auf die Normalen im Vertex Shader an.
4. Zeichnen Sie neben dem ersten Objekt ein weiteres mit Phong-Schattierung. Verwenden Sie dazu für jedes Schattierungsverfahren ein separates Shader-Programm.

Nach Bearbeitung dieser Aufgaben sollten Sie in der Lage sein, ...

- mehreren Shader-Programme in einem Projekt zu verwenden.
- die Bedeutung von Normalenvektoren für die Beleuchtung zu verstehen.
- Gouraud- und Phong-Schattierung zu implementieren.

## Exercise 5 - Texturen

Bereichern Sie Ihre Anwendung visuell durch die Verwendung von Texturen und interaktiv durch Tastureingaben. Verwenden Sie das zur Verfügung gestellte Rahmenwerk.

1. Ergänzen Sie ihre Objekte um UV-Koordinaten pro Ecke.
2. Schreiben Sie ein Shader-Programm zur Verwendung von Texturen und verwenden Sie dieses mit mehreren Texturen auf Ihren Objekten.
3. Beobachten Sie die unterschiedlichen Auswirkungen der Parameter für Filterung und MIP-Mapping. Finden Sie diese selbständig in der Dokumentation. Um die Effekte deutlich sehen zu können, sollten Sie mindestens eine "riesige" Textur (z.B. 2048x2048) und eine "winzige" Textur (z.B. 16x16) verwenden.
4. Kombinieren Sie Ihr Textur-Shader-Programm mit Phong-Schattierung aus der letzten Übung.
5. Schreiben Sie ein weiteres Shader-Programm mit einer primitiven prozeduralen Textur, z.B. einem einfachen Muster. D.h. es existiert keine Bilddatei für diese "Textur", sondern ein Muster wird über eine Funktion abhängig von den uv-Koordinaten generiert.
6. Implementieren Sie eine Tastaturabfrage in Java mit Hilfe des GLFW-Frameworks und erlauben Sie damit einfache Interaktion. (Bonuspunkte!)

Nach Bearbeitung dieser Aufgaben sollten Sie in der Lage sein, ...

- Texturen zusammen mit Beleuchtung zu verwenden.
- die praktischen Auswirkungen der unterschiedlichen Texturfilter zu erklären.
- eventuell einfache Interaktion über die Tastatur zu implementieren.

## Voraussetzungen Prüfungsprojekt

Folgende Funktionen muss Ihr Programm alle in mindestens einer Ausführung für die Bestnote enthalten:

- Zwei verschiedene "volldimensionale" 3D-Objekte (ggf. in mehreren Ausführungen)
- Verwendung von Transformationen (translate, rotate, scale) für eine einfache Animation (z.B. fortlaufende Transformation)
- Verschiedene Texturen in unterschiedlichen Auflösungen und mit unterschiedlichen Filtern (Bilddateien im Projekt enthalten!)
- Vertex- und Fragment-Shader für Phong-Schattierung (zählt wegen des hohen Aufwandes doppelt)
- Vertex- und Fragment-Shader für eine simple prozedurale Textur
- Interaktionsmöglichkeit über Tastatur oder Maus (siehe Web-Tutorial, gibt einen Bonuspunkt!)
Für auffällig darüber hinausgehende Lösungen sind Bonuspunkte denkbar.
