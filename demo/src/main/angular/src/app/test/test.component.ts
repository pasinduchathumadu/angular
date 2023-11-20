// test.component.ts

import { Component, OnInit } from '@angular/core';
import { AddService } from '../add.service';
import { addbook,FormDataModel } from '../add';
// @ts-ignore
import _ from "lodash";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  public datalist: addbook[] = [ ];
  public setval:boolean= false;
  public load:boolean = false
  errorMessage = '';
  postedArray:String[] = [];

  bookname:string= '';
  author: string = '';
  value: string = '';
  price : number = 0;


  constructor(private addservice: AddService) {}

  ngOnInit() {
    this.addservice.getlist().subscribe(
      (data: addbook[]) => this.datalist = data,
      (error: any) => this.errorMessage = error
    );
    this.addservice.getCategory().subscribe((data)=>{
      this.postedArray = data
    },(error)=>{
      this.errorMessage = error
    })


  }

  onSubmit() {
    const bookData :FormDataModel = {
      bookName: this.bookname,
      author: this.author,
      category: this.value,
      price: this.price
    };
    if (_.isEmpty(bookData.bookName)|| _.isEmpty(bookData.author) || _.isEmpty(bookData.category)){
      this.load = true;
      this.errorMessage = "Please be filled!!"
      return

    }
    else{
      this.setval = true;
      this.load = true;
      this.addservice.addList(bookData).subscribe(
        response => {
          console.log('Form data submitted successfully:', response);
          this.errorMessage = "Form data submitted successfully";
        },
        error => {
          console.error('Form data submission failed:', error);
          this.errorMessage = error;
          // Handle errors
        }
      );

    }


  }

}
