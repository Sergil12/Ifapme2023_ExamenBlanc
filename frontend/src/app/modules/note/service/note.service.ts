import { Injectable } from '@angular/core';
import { ApiService } from '@shared/service';
import { Observable } from 'rxjs/internal/Observable';
import { Note } from '../model/business/note.business';
import { ApiResponse, ApiUriEnum } from '@shared/model';
import { map, switchMap } from 'rxjs/operators';
import { NoteDto } from '../model/dto/note.dto';
import { NoteHelper } from '../helper/note-helper';
import { NoteCreatePayload } from '../model/payload/note-create.payload';
import { NoteUpdatePayload } from '../model/payload/note-update.payload';

@Injectable({
  providedIn: 'root',
})
export class NoteService extends ApiService {
  list(): Observable<Note[]> {
    return this.get(ApiUriEnum.NOTE_LIST).pipe(
      map((response: ApiResponse) =>
        response.result
          ? (response.data! as NoteDto[]).map((dto: NoteDto) =>
              NoteHelper.fromDTO(dto)
            )
          : []
      )
    );
  }
  detail(id: string): Observable<Note> {
    return this.get(ApiUriEnum.NOTE_DETAIL + id).pipe(
      map((response: ApiResponse) =>
        response.result
          ? NoteHelper.fromDTO(response.data! as NoteDto)
          : NoteHelper.getEmpty()
      )
    );
  }
  create(payload: NoteCreatePayload): Observable<Note> {
    return this.post(ApiUriEnum.NOTE_CREATE, payload).pipe(
      map((response: ApiResponse) =>
        response.result
          ? NoteHelper.fromDTO(response.data! as NoteDto)
          : NoteHelper.getEmpty()
      )
    );
  }
  update(payload: NoteUpdatePayload): Observable<Note> {
    return this.put(ApiUriEnum.NOTE_UPDATE, payload).pipe(
      map((response: ApiResponse) =>
        response.result
          ? NoteHelper.fromDTO(response.data! as NoteDto)
          : NoteHelper.getEmpty()
      )
    );
  }

  remove(id: string): Observable<Note[]> {
    return this.delete(ApiUriEnum.NOTE_DELETE + id).pipe(
      switchMap(() => this.list())
    );
  }
}
