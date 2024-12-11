import { view } from './view.mjs';
import * as model from './model.mjs';
import querystring from 'node:querystring';

export const handler = async (event, content) => {

  // GET
  if (event.requestContext.http.method == 'GET') {
    return await view();
  }

  // POST
  else if (event.requestContext.http.method == 'POST') {

    const postData = querystring.parse(atob(event.body));
    let command = postData.command;

    switch (command) {

      case "ADD":
        return await model.addMemo(postData.memo);

      case "SEARCH":
        return await model.searchMemos(postData.term);

      case "DELETE":
        return await model.deleteMemo(postData.id);

    }

  }

}