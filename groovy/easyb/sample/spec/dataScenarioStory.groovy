import sample.Data

scenario "���O�������Ă���", {
    given "Data �̏�����", {
        data = new Data("test")
    }

    then "�R���X�g���N�^�Őݒ肳�ꂽ�l�����O�ƂȂ�ׂ�", {
        data.getName().shouldBe "test"
    }
}

scenario "���O�͕ύX�ł��Ȃ�", {
    given "Data �̏�����", {
        data = new Data("test")
    }

    when "���O��ύX����", {
        setname = {
            data.setName("aaa")
        }
    }

    then "��O���������ׂ�", {
        ensureThrows(Exception) {
            setname()
        }
    }
}
