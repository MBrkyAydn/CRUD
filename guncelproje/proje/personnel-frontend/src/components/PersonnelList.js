import React from 'react';
import { deletePersonnel } from '../api'; // İmport ifadesini kontrol edin
import { Table, Button } from 'react-bootstrap';

const PersonnelList = ({ personnelList, onRefresh, onEdit }) => {
    const handleDelete = async (id) => {
        await deletePersonnel(id);
        onRefresh();
    };

    return (
        <div>
            <h2>Personnel List</h2>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>İsim</th>
                        <th>Email</th>
                        <th>Seçenekler</th>
                    </tr>
                </thead>
                <tbody>
                    {personnelList.length > 0 ? (
                        personnelList.map(person => (
                            <tr key={person.id}>
                                <td>{person.name}</td>
                                <td>{person.email}</td>
                                <td>
                                    <Button variant="warning" onClick={() => onEdit(person)}>Düzenle</Button>{' '}
                                    <Button variant="danger" onClick={() => handleDelete(person.id)}>Sil</Button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="3" className="text-center">Mevcut Personel Yok.</td>
                        </tr>
                    )}
                </tbody>
            </Table>
        </div>
    );
};

export default PersonnelList;
