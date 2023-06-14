import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { isNil } from 'lodash';
import { BehaviorSubject, of } from 'rxjs';
import { switchMap, tap } from 'rxjs/operators';
import { StudentHelper } from '../../helper/student-helper';
// @ts-ignore
import { Student} from '../../model/business/student.business';
import { StudentUpdatePayload } from '../../model/payload/student-update.payload';
import { StudentService } from '../../service/student.service';

class Student {
    firstname: any;
  Student_id: string | undefined;
  isEmpty: any;
  student: any;
}

class student {
  isEmpty: any;
}

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss'],
})
export class StudentDetailsComponent implements OnInit {
  formGroup!: FormGroup;
  student$: BehaviorSubject<Student> = new BehaviorSubject<Student>(
    StudentHelper.getEmpty()
  );
  formMode: string = 'CREATE';
  error: string = '';

  constructor(
    public activatedRouter: ActivatedRoute,
    public router: Router,
    public studentService: StudentService
  ) {}

  ngOnInit(): void {
    this.activatedRouter.params
      .pipe(
        switchMap((params: Params) => {
          // tester si param['id'] != 0 pourquoi? parce que pour créer un element je vais appeller cette page en
          // donnant comme id 0
          if (!isNil(params['id']) && params['id'] != '0') {
            return this.studentService.detail(params['id']);
          }
          return of(StudentHelper.getEmpty());
        }),
        // ici on va créer le formulaire
        tap((student: Student) => {
          // on définit dans quelle mode on est : creation ou modification
          this.formMode = student.isEmpty ? 'CREATE' : 'UPDATE';
          this.student$.next(student);
          // on remplit le formgroup
          this.formGroup = new FormGroup({
            firstname: new FormControl(student.firstname, [
              Validators.required,
            ]),
            student: new FormControl(student.student, [Validators.required]),
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
        this.studentService
          .create(this.formGroup.value)
          .subscribe((result: student) => {
            if (result.isEmpty) {
              this.error = 'Erreur lors de la création.';
            }
            this.router.navigate(['/Student/all']).then();
          });
      } else {
        const payload: StudentUpdatePayload = this.formGroup.value;
        payload.student_id = <string>this.student$.getValue().Student_id;
        this.studentService.update(payload).subscribe((result: student) => {
          if (result.isEmpty) {
            this.error = 'Erreur lors de la modification.';
          }
          this.router.navigate(['/Student/all']).then();
        });
      }
    }
  }
}
