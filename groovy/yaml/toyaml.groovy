import org.ho.yaml.*

println Yaml.dump("test")

list = ["�e�X�g", 3, 100, [name: "a", test: 1]]

yamlList = Yaml.dump(list)

println yamlList

println Yaml.load(yamlList)
