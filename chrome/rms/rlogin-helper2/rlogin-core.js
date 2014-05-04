window.addEventListener('load', function() {

	var createData = function(src) {
		var res = {};

		PARAMETER_KEYS.forEach(function(key) {
			res[key] = (src && src[key])? src[key]: '';
		});

		return res;
	};

	var saveSettings = function(target) {
		var data = JSON.stringify(target());
		localStorage.setItem(ST_KEY, data);
	};

	var loadSettings = function(target) {
		var settings = localStorage.getItem(ST_KEY);

		if (settings) {
			try {
				var data = JSON.parse(settings);
				target(data);
			} catch (e) {
				console.error(e);
			}
		}
	};

	var rlogin = function(item) {
		if (item) {
			chrome.tabs.getSelected(null, function(tab) {
				var oldAction = '';

				var listener = function(id, inf) {
					if (tab.id == id && inf.status == 'complete') {
						var params = item;

						chrome.tabs.sendMessage(tab.id, params, function(res) {
							// �������������邩�A���������y�[�W�� 2�x�\�������Β�~
							if (res == 'end' || res == oldAction) {
								chrome.tabs.onUpdated.removeListener(listener);
							}
							oldAction = res;
						});
					}
				};

				chrome.tabs.onUpdated.addListener(listener);

				// callback �֐����� sendMessage ���g�p�����
				// �y�[�W�ǂݍ��݊����O�ɃX�N���v�g�����s�����̂œs��������
				chrome.tabs.update(tab.id, { url: LOGIN_URL });
			});
		}
	};

	var trim = function(str) {
		return (str)? str.replace(/(^\s+)|(\s+$)/g, ''): str;
	};

	var viewModel = {
		title: ko.observable(),
		items: ko.observableArray(),
		selectedItem: ko.observable(),
		addItem: function() {
			var title = trim(viewModel.title());

			if (title) {
				viewModel.items.push(createData({ title: title }));
				viewModel.title('');
				saveSettings(viewModel.items);
			}
		},
		updateItem: function() {
			var item = viewModel.selectedItem();

			if (item) {
				saveSettings(viewModel.items);
			}
		},
		removeItem: function() {
			var item = viewModel.selectedItem();

			if (item) {
				viewModel.items.remove(item);
				saveSettings(viewModel.items);
			}
		},
		doLogin: function() {
			rlogin(viewModel.selectedItem());
		}
	};

	loadSettings(viewModel.items);

	ko.applyBindings(viewModel);
});
