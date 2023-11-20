import {Component} from '@angular/core';
import {AddService} from "../add.service";
import { addbook} from '../add';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrl: './update.component.css'
})
export class UpdateComponent {
  datalist:addbook[] = [];
  private errorMessage: string = "";
  search: any;
  searchBook :any;


  constructor(private addService: AddService) {
  }

  ngOnInit() {
    this.errorMessage = "";
    this.addService.getlist().subscribe((data)=>{
      this.datalist = data;

    },
      (error)=>{
        this.errorMessage = error;
      }
    )

  }

  searchItem() {


    this.addService.seraachItem(this.search).subscribe((data)=>{
      this.datalist = data;
    })
  }
  refresh(){
    this.addService.refresh().subscribe((data)=>{
      this.datalist = data
    },(error)=>{
      this.errorMessage = error
    })
  }
  searchItemBookName(){

    this.addService.seraachItemBook(this.searchBook).subscribe((data)=>{
      this.datalist = data;
    })
  }
}
