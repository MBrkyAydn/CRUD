import React, { useState, useEffect } from 'react';
import { createPersonnel, updatePersonnel } from '../api';
import { Modal, Button, Form } from 'react-bootstrap';

const PersonnelForm = ({ show, handleClose, selectedPersonnel }) => {
    const [personnel, setPersonnel] = useState({ name: '', email: '' });

    useEffect(() => {
        if (selectedPersonnel) {
            setPersonnel(selectedPersonnel);
        } else {
            setPersonnel({ name: '', email: '' });
        }
    }, [selectedPersonnel]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setPersonnel(prevPersonnel => ({ ...prevPersonnel, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (personnel.id) {
            updatePersonnel(personnel.id, personnel)
                .then(response => {
                    handleClose();
                })
                .catch(error => {
                    console.error('Güncelleme Hatası:', error);
                });
        } else {
            createPersonnel(personnel)
                .then(response => {
                    handleClose();
                })
                .catch(error => {
                    console.error('Oluşturma Hatası:', error);
                });
        }
    };

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>{selectedPersonnel ? 'Personel Güncelle' : 'Personel Ekle'}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formName">
                        <Form.Label>İsim</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={personnel.name}
                            onChange={handleChange}
                            placeholder="İsim Giriniz"
                        />
                    </Form.Group>
                    <Form.Group controlId="formEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            type="email"
                            name="email"
                            value={personnel.email}
                            onChange={handleChange}
                            placeholder="Email Giriniz"
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Kaydet
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
};

export default PersonnelForm;
