async function addMemo(memo) {
     return 'ADD: OK - ' + memo;
 }
 
 async function searchMemos(term) {
     return 'SEARCH: OK - ' + term;
 }
 
 async function deleteMemo(id) {
     return 'DELETE: OK - ' + id;
 }
 
 export { addMemo, searchMemos, deleteMemo };