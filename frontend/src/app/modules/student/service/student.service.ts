import { Injectable } from '@angular/core';
import { ApiService } from '@shared/service';
import { Observable } from 'rxjs/internal/Observable';
import { Student } from '../model/business/student.business';
import { ApiResponse, ApiUriEnum } from '@shared/model';
import { map, switchMap } from 'rxjs/operators';
import { StudentDto } from '../model/dto/student.dto';
import { StudentHelper } from '../helper/student-helper';
import { StudentCreatePayload } from '../model/payload/student-create.payload';
import { StudentUpdatePayload } from '../model/payload/student-update.payload';

@Injectable({
  providedIn: 'root',
})
export class StudentService extends ApiService {

  detail(id: string): Observable<Student> {
    return this.get(ApiUriEnum.STUDENT_DETAIL + id).pipe(
      map((response: ApiResponse) =>
        response.result
          ? StudentHelper.fromDTO(response.data! as StudentDto)
          : StudentHelper.getEmpty()
      )
    );
  }
  create(payload: StudentCreatePayload): Observable<Student> {
    return this.post(ApiUriEnum.Student_CREATE, payload).pipe(
      map((response: ApiResponse) =>
        response.result
          ? StudentHelper.fromDTO(response.data! as StudentDto)
          : StudentHelper.getEmpty()
      )
    );
  }
  update(payload: StudentUpdatePayload): Observable<Student> {
    return this.put(ApiUriEnum.STUDENT_UPDATE, payload).pipe(
      map((response: ApiResponse) =>
        response.result
          ? StudentHelper.fromDTO(response.data! as StudentDto)
          : StudentHelper.getEmpty()
      )
    );
  }

  remove(id: string) {
    return this.delete(ApiUriEnum.STUDENT_DELETE + id).pipe();
  }
}
