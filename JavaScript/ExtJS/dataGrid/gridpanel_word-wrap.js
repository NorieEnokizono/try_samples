
Ext.onReady(function() {
    var data = [
        ['�Ȃ��A���ۂ� SproutCore �� sc-build �R�}���h�Ő������� HTML �� JavaScript �� Jersey �̃��\�[�X�N���X���Ƌ��� WAR �t�@�C�������ăf�v���C����悤�ȗ���ɂȂ�Ǝv�����A����͂����܂ł͂�炸�A�A�g���@���m�F����܂łɎ~�߂Ă���B', 'todo.png'],
        ['���g���N�X���茋��', 'metrics_results.png']
    ];

    var store = new Ext.data.SimpleStore({
        fields: [
            {name: 'title'},
            {name: 'image'}
        ]
    });

    store.loadData(data);

    var grid = new Ext.grid.GridPanel({
        columns: [
            {header: 'Title', sortable: true, dataIndex: 'title', width: 100},
            {id: 'image', header: 'Image', dataIndex: 'image', 
                renderer: function(value){
                    var tpl = new Ext.Template("<img width='{width}' height='{height}' src='{img}'></img>");
                    return tpl.apply({img: value, width: 300, height: 30});
                }
            }
        ],
        store: store,
        renderTo: 'grid-sample',
        width: 450,
        height: 250,
        stripeRows: true,
        autoExpandColumn: 'image'
    });
});