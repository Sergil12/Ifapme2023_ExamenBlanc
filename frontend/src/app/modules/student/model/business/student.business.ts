import { isEmpty } from '@shared/model';

export interface Student extends isEmpty {
  student: Student;
  student_id: string;
  firstname: string;
  lastname: string;
}
