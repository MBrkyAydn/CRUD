import React, { useState, useEffect } from 'react';
import PersonnelForm from './PersonnelForm';
import PersonnelList from './PersonnelList';
import { getAllPersonnel } from '../api';

const MainScreen = () => {
    const [personnel, setPersonnel] = useState([]);
    const [refresh, setRefresh] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [selectedPersonnel, setSelectedPersonnel] = useState(null);

    const fetchPersonnel = async () => {
        try {
            const response = await getAllPersonnel();
            setPersonnel(response.data);
        } catch (error) {
            console.error('Fetch personnel error:', error);
        }
    };

    useEffect(() => {
        fetchPersonnel();
    }, [refresh]);

    const handleSave = () => {
        setRefresh(!refresh);
        setShowModal(false);
    };

    const handleEdit = (person) => {
        setSelectedPersonnel(person);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setSelectedPersonnel(null);
        setShowModal(false);
    };

    return (
        <div className="container mt-5">
            <h1>Personel Yönetim Paneli</h1>
            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-success" onClick={() => setShowModal(true)}>Yeni Personel Ekle</button>
                <button className="btn btn-primary ml-2" onClick={fetchPersonnel}>Listeyi Yenile</button>
            </div>
            <PersonnelList personnelList={personnel} onRefresh={handleSave} onEdit={handleEdit} />
            <PersonnelForm show={showModal} handleClose={handleCloseModal} selectedPersonnel={selectedPersonnel} />
        </div>
    );
};

export default MainScreen;
