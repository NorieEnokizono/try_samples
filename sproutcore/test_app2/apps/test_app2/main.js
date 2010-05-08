// ==========================================================================
// Project:   TestApp2
// Copyright: ©2009 My Company, Inc.
// ==========================================================================
/*globals TestApp2 */

// This is the function that will start your app running.  The default
// implementation will load any fixtures you have created then instantiate
// your controllers and awake the elements on your page.
//
// As you develop your application you will probably want to override this.
// See comments for some pointers on what to do next.
//

TestApp2.main = function main() {
  // Step 1: Instantiate Your Views
  // The default code here will make the mainPane for your application visible
  // on screen.  If you app gets any level of complexity, you will probably 
  // create multiple pages and panes.  
  TestApp2.getPath('mainPage.mainPane').append() ;

  //TestApp2.store = SC.Store.create().from(TestApp2.JerseyDataSource.create());

  // Step 2. Set the content property on your primary controller.
  // This will make your app come alive!

  TestApp2.tasksController.set('content', TestApp2.store.find(TestApp2.Task));
  
  //TestApp2.store.set('commitRecordsAutomatically', YES); 
  // TODO: Set the content property on your primary controller
  // ex: TestApp2.contactsController.set('content',TestApp2.contacts);

} ;

function main() { TestApp2.main(); }
