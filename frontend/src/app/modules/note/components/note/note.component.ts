import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Note } from '../../model/business/note.business';
import { NoteService } from '../../service/note.service';
import { AuthService } from '@security/service/auth.service';
import { CredentialHelper } from '@security/helper';
import { Credential, CredentialDto } from '@security/model';
import { ApiResponse } from '@shared/model';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss'],
})
export class NoteComponent implements OnInit {
  list$ = new BehaviorSubject<Note[]>([]);
  credential?: Credential;
  formMode: Boolean | undefined;

  constructor(
    public router: Router,
    public noteService: NoteService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.noteService
      .list()
      .pipe(tap((list: Note[]) => this.list$.next(list)))
      .subscribe(
        (response: any) => {
          console.log('response Note : ', response);
        },
        (err: Error) => {
          console.log('errNote : ', err);
        }
      );
  }

  delete(id: string) {
    this.noteService
      .remove(id)
      .pipe(tap((list: Note[]) => this.list$.next(list)))
      .subscribe();
  }

  create(): void {
    this.router.navigate(['/note/details/0']).then();
  }

  details(id: string): void {
    this.router.navigate(['/note/details/' + id]).then();
  }

  me(): void {
    this.auth.me().subscribe((response: ApiResponse) => {
      this.credential = CredentialHelper.credentialFromDto(
        response.data as CredentialDto
      );
      console.log('this.credential', this.credential);
    });
  }

  logout(): void {
    this.auth.logout();
  }

  gotoStudent() {
    this.router.navigate(['/student']);
  }
}
