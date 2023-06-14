import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NoteComponent } from './components/note/note.component';
import { NoteDetailsComponent } from './components/note-details/note-details.component';

const routes: Routes = [
  { path: '', redirectTo: 'all', pathMatch: 'full' },
  {
    path: 'all',
    component: NoteComponent,
  },
  {
    path: 'details/:id',
    component: NoteDetailsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NoteRoutingModule {}
