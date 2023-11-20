import { NgModule } from '@angular/core';

import { Routes , RouterModule } from '@angular/router';
import {BookComponent} from './book/book.component'
import { TestComponent } from './test/test.component';
import { HomeComponent } from './home/home.component';
import  {UpdateComponent} from "./update/update.component";


export const routes : Routes = [
  {path:'',redirectTo:'',pathMatch:'full'},
  {path:'',component:HomeComponent},
  {path:'add',component:TestComponent},
  {path:'viewBook',component:BookComponent},
  {path:'updateBook',component:UpdateComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [HomeComponent,TestComponent,BookComponent,UpdateComponent]
