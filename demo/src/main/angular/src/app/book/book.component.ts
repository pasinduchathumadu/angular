import {Component, OnInit} from '@angular/core';
import {AddService} from "../add.service";
import { addbook } from '../add';
import { updatePrice } from '../add';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrl: './book.component.css',

})
export class BookComponent implements OnInit {
  isOpen: boolean = false;
  errorMessage: string = "";

  books :addbook[] = [];

  newPrice1: number | undefined
  selectedId : number | undefined;
  public totalCount: number = 0
  constructor(private addService: AddService) {}

  ngOnInit(): void {
    this.errorMessage = "";
    this.addService.getlist().subscribe((data)=>{
      this.books = data;

    },
      (error)=>{
        this.errorMessage = error;
      }
    )
    this.addService.getCount().subscribe((data)=>{
      this.totalCount = data;
    },(error)=>{
      this.errorMessage = error;
    })

  }

  removeUser(Id:number) {
    this.addService.removeBook(Id).subscribe((data)=>{
      this.books = data
    },(error)=>{
      this.errorMessage = error;
    })
  }

  onupdate(id:number,) {
    this.isOpen = true;
    this.selectedId = id;
  }

  onSave() {
    if (this.newPrice1 === undefined || this.selectedId === undefined) {
      this.errorMessage = "Not updated";
      return
    }
    const updateBook :updatePrice ={
      price :this.newPrice1
    }
    this.addService.update(this.selectedId,updateBook).subscribe((response)=>{
      this.books = response,
      this.errorMessage = "Successfully Updated";
    },(error)=>{
      this.errorMessage = "Please be updated";
    })


  }

  closeModal() {
    this.errorMessage = "";
    this.isOpen = false;
  }
}
