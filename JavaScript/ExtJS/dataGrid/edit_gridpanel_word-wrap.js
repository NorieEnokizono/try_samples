
Ext.onReady(function() {
    var data = [
        ['�Ȃ��A���ۂ� SproutCore �� sc-build �R�}���h�Ő������� HTML �� JavaScript �� Jersey �̃��\�[�X�N���X���Ƌ��� WAR �t�@�C�������ăf�v���C����悤�ȗ���ɂȂ�Ǝv�����A����͂����܂ł͂�炸�A�A�g���@���m�F����܂łɎ~�߂Ă���B', 'todo.png', 'test'],
        ['���g���N�X���茋��', 'metrics_results.png', 'aaa']
    ];

    var store = new Ext.data.SimpleStore({
        fields: [
            {name: 'title'},
            {name: 'image'},
            {name: 'note'}
        ]
    });

    store.loadData(data);

    var grid = new Ext.grid.EditorGridPanel({
        columns: [
            {id: 'title', header: 'Title', sortable: true, dataIndex: 'title', width: 100,
            	editor: new Ext.grid.GridEditor(new Ext.form.TextArea(), {autoSize: true})},
            {id: 'image', header: 'Image', dataIndex: 'image', 
                renderer: function(value){
                    var tpl = new Ext.Template("<img width='{width}' height='{height}' src='{img}'></img>");
                    return tpl.apply({img: value, width: 300, height: 30});
                }
            },
            {id: 'note', header: 'Note', sortable: true, dataIndex: 'note', width: 100,
            	editor: new Ext.grid.GridEditor(new Ext.form.TextArea(), {autoSize: true})}
        ],
        store: store,
        renderTo: 'grid-sample',
        width: 450,
        height: 250,
        stripeRows: true,
        autoExpandColumn: 'image'
    });
});