// add.service.ts

import {Injectable} from '@angular/core'
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {addbook, FormDataModel,updatePrice} from './add'; // Import the addbook model if you have it

@Injectable({
  providedIn: 'root'
})
export class AddService {
  private _url = 'http://localhost:8080/api/v1/books';
  private _urlDelete = 'http://localhost:8080/api/v1/deleteBook/'
  private _urlUpdate = 'http://localhost:8080/api/v1/updateBook/'
  private _searchItemAuthor = 'http://localhost:8080/api/v1/search/'
  private _searchItemBook = 'http://localhost:8080/api/v1/searchBookName/'
  private  _addBook = 'http://localhost:8080/api/v1/addBook'
  private _selectCategory = 'http://localhost:8080/api/v1/selectCategory'
  private  _selectCount = 'http://localhost:8080/api/v1/SelectCount'
  private isSet: boolean = false



  constructor(private http: HttpClient) {
  }

  getlist(): Observable<addbook[]> {
    return this.http.get<addbook[]>(`${this._url}`).pipe(
      catchError(this.errorHandler)
    );
  }


 getCategory():Observable<any>{
    return this.http.get(`${this._selectCategory}`).pipe(catchError(this.errorHandler));
 }

  addList(formData: FormDataModel): Observable<any> {
    return this.http.post(this._addBook, formData).pipe(catchError(this.errorHandler))

  }

  private errorHandler(error: HttpErrorResponse): Observable<never> {
    return throwError(error || 'Something went wrong, please try again later.');
  }

  removeBook(Id:number):Observable<addbook[]> {
    return this.http.delete<addbook[]>(`${this._urlDelete}${Id}`).pipe(catchError(this.errorHandler));

  }

  seraachItem(searchElement:String):Observable<addbook[]> {
    return this.http.get<addbook[]>(`${this._searchItemAuthor}${searchElement}`).pipe(catchError(this.errorHandler));

  }

  seraachItemBook(searchElement:String):Observable<addbook[]> {
    return this.http.get<addbook[]>(`${this._searchItemBook}${searchElement}`).pipe(catchError(this.errorHandler));

  }

  update(id:number,updateBook:updatePrice):Observable<addbook[]> {
   return this.http.put<addbook[]>(`${this._urlUpdate}${id}`,updateBook).pipe(catchError(this.errorHandler));

  }
  getCount(){
    return this.http.get<number>(`${this._selectCount}`).pipe(catchError(this.errorHandler));

  }
  refresh():Observable<addbook[]> {
    return this.http.get<addbook[]>(`${this._url}`).pipe(catchError(this.errorHandler));
  }
}
