import org.ho.yaml.*

//YAML
str = """
- �e�X�g
- 100
- test: 1
  name: "abc"
"""

obj = Yaml.load(str)
println obj

