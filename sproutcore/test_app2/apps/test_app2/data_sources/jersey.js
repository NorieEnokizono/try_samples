// ==========================================================================
// Project:   TestApp2.JerseyDataSource
// Copyright: ©2009 My Company, Inc.
// ==========================================================================
/*globals TestApp2 */

/** @class

  (Document Your Data Source Here)

  @extends SC.DataSource
*/
TestApp2.JerseyDataSource = SC.DataSource.extend(
/** @scope TestApp2.JerseyDataSource.prototype */ {

  // ..........................................................
  // QUERY SUPPORT
  // 

  fetch: function(store, query) {
	SC.Request.getUrl("/tasks").json().notify(this, function(res) {
		if (SC.ok(res)) {
			//JSON �ǡ������������ˤ� res.get('body') ��Ȥ�
			store.loadRecords(TestApp2.Task, res.get('body').task);
			store.dataSourceDidFetchQuery(query);
		}
		else {
			store.dataSourceDidErrorQuery(query, res);
		}
	}).send();

	return YES;
  },

  // ..........................................................
  // RECORD SUPPORT
  // 
  
  retrieveRecord: function(store, storeKey) {
    
    // TODO: Add handlers to retrieve an individual record's contents
    // call store.dataSourceDidComplete(storeKey) when done.
    
    return NO ; // return YES if you handled the storeKey
  },
  
  createRecord: function(store, storeKey) {
	var obj = store.readDataHash(storeKey);

	SC.Request.postUrl("/tasks").json().notify(this, function(res) {
		if (SC.ok(res)) {
			var url = res.header('Location');
			var paths = url.split("/");

			//Location �ϥե�ѥ��ʤΤ����Ƥ����ꤹ���
			//�������˻��Ѥ��� URL �����꤬�Ф�
			store.dataSourceDidComplete(storeKey, null, paths[paths.length - 1]);
		}
		else {
			store.dataSourceDidError(storeKey, res);
		}
	}).send(obj);

	return YES;
  },
  
  updateRecord: function(store, storeKey) {
	var obj = store.readDataHash(storeKey);

	SC.Request.putUrl("/tasks/" + store.idFor(storeKey)).json().notify(this, function(res) {
		if (SC.ok(res)) {
			store.dataSourceDidComplete(storeKey);
		}
		else {
			store.dataSourceDidError(storeKey);
		}
	}).send(obj);

	return YES;
  },
  
  destroyRecord: function(store, storeKey) {
    
    // TODO: Add handlers to destroy records on the data source.
    // call store.dataSourceDidDestroy(storeKey) when done
    
    return NO ; // return YES if you handled the storeKey
  }
  
}) ;
