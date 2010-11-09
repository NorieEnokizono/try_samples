baseDir = System.getProperty("base.dir")

//�C�x���g�����̒�`
eventCreatedArtefact = {type, name ->

	storyFileBody = """
scenario "$name", {
	when "", {
	}

	then "", {
	}
}
"""
	if (type == "Domain Class") {
		new File("${baseDir}/spec/${name}Scenario.story").withWriter {
			it.write(storyFileBody);
		}
	}
}
