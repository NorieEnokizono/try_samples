
import sample.Data

before "Data �̏�����", {
    data = new Data("test")
}

it "���O�������Ă���", {
    data.getName().shouldBe "test"
}

it "���O�͕ύX�ł��Ȃ�", {
    ensureThrows(Exception) {
        data.setName("aaa")
    }
}