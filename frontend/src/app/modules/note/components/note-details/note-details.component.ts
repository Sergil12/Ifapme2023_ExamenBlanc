import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { isNil } from 'lodash';
import { BehaviorSubject, of } from 'rxjs';
import { switchMap, tap } from 'rxjs/operators';
import { NoteHelper } from '../../helper/note-helper';
import { Note } from '../../model/business/note.business';
import { NoteUpdatePayload } from '../../model/payload/note-update.payload';
import { NoteService } from '../../service/note.service';

@Component({
  selector: 'app-note-details',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.scss'],
})
export class NoteDetailsComponent implements OnInit {
  formGroup!: FormGroup;
  note$: BehaviorSubject<Note> = new BehaviorSubject<Note>(
    NoteHelper.getEmpty()
  );
  formMode: string = 'CREATE';
  error: string = '';

  constructor(
    public activatedRouter: ActivatedRoute,
    public router: Router,
    public noteService: NoteService
  ) {}

  ngOnInit(): void {
    this.activatedRouter.params
      .pipe(
        switchMap((params: Params) => {
          // tester si param['id'] != 0 pourquoi? parce que pour créer un element je vais appeller cette page en
          // donnant comme id 0
          if (!isNil(params['id']) && params['id'] != '0') {
            return this.noteService.detail(params['id']);
          }
          return of(NoteHelper.getEmpty());
        }),
        // ici on va créer le formulaire
        tap((note: Note) => {
          // on définit dans quelle mode on est : creation ou modification
          this.formMode = note.isEmpty ? 'CREATE' : 'UPDATE';
          this.note$.next(note);
          // on remplit le formgroup
          this.formGroup = new FormGroup({
            cursus_name: new FormControl(note.cursus_name, [
              Validators.required,
            ]),
            note: new FormControl(note.note, [Validators.required]),
          });
        })
      )
      .subscribe();
  }

  submit(): void {
    // toujours regarder si le formulaire est valide ou non?
    if (this.formGroup.invalid) {
      this.error = 'Il y a une erreur dans le formulaire.';
    } else {
      if (this.formMode === 'CREATE') {
        // on cree
        this.noteService
          .create(this.formGroup.value)
          .subscribe((result: Note) => {
            if (result.isEmpty) {
              this.error = 'Erreur lors de la création.';
            }
            this.router.navigate(['/note/all']).then();
          });
      } else {
        const payload: NoteUpdatePayload = this.formGroup.value;
        payload.note_id = this.note$.getValue().note_id;
        this.noteService.update(payload).subscribe((result: Note) => {
          if (result.isEmpty) {
            this.error = 'Erreur lors de la modification.';
          }
          this.router.navigate(['/note/all']).then();
        });
      }
    }
  }
}
